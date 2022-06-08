import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { Button, Form, Table } from "react-bootstrap";
import { api } from "./api";
import { Loading } from "./components/Loading";

function App() {
  const [showLoading, setShowLoading] = useState(false);
  const [pathResource, setPathResource] = useState("/relational-db");
  const [userQuantitySimulation, setUserQuantitySimulation] = useState(1);
  const [products, setProducts] = useState([]);
  const [metrics, setMetrics] = useState({
    lastTime: 0,
    avgTime: 0,
    totalTime: 0,
    memoryUsage: 0,
    cpuUsage: 0,
  });

  const handleSearchButtonClick = async () => {
    let lastTime = 0;
    let totalTime = 0;
    let memoryUsage = 0;
    let cpuUsage = 0;
    setShowLoading(() => true);
    let products = [];
    const path = `${pathResource}/products`;
    for (let i = 0; i < userQuantitySimulation; i++) {
      const { data } = await api.get(path);
      products = data;
      lastTime = await getLastRequestTime();
      console.log(`LAST TIME - ${i} -`, lastTime);
      totalTime += lastTime;
    }
    setProducts(() => products);
    const avgTime = totalTime / userQuantitySimulation;
    handleMetrics(lastTime, avgTime, totalTime, memoryUsage, cpuUsage);
    setShowLoading(() => false);
  };

  const handleMetrics = (
    lastTime,
    avgTime,
    totalTime,
    memoryUsage,
    cpuUsage
  ) => {
    console.log(
      "CAIU NA HANDLEMETRICAS ",
      lastTime,
      avgTime,
      totalTime,
      memoryUsage,
      cpuUsage
    );
    setMetrics((prev) => {
      return {
        ...prev,
        lastTime,
        avgTime,
        totalTime,
        memoryUsage,
        cpuUsage,
      };
    });
  };

  const getLastRequestTime = async () => {
    let requestTime = 0;

    const { data } = await api.get(`/actuator/metrics${pathResource}`);

    if (data.measurements) {
      data.measurements.forEach((m) => {
        switch (m.statistic) {
          case "COUNT":
            // Essa estatística representa o número de requisições total em determinado end-point no servidor
            break;
          case "TOTAL_TIME":
            // Essa estatística representa o tempo total somado de todas as requisições feitas no servidor.
            break;
          case "MAX":
            // Essa estatística representa o último valor obtido pela última requisição realizada.
            requestTime = m.value;
            break;
        }
      });
    }

    return requestTime;
  };

  const handleOptionChange = (e) => {
    const { value } = e.target;
    setPathResource(() => value);
    setUserQuantitySimulation(() => 1);
    setProducts(() => []);
    setMetrics(() => ({
      avgTime: 0,
      totalTime: 0,
      memoryUsage: 0,
      cpuUsage: 0,
    }));
  };

  return (
    <div className="App">
      <header>
        <p>Projeto integrador II</p>
        <p>Desenvolvido por: Rafhael Andrade e Guilherme Silveira</p>
      </header>
      <div className="App-main">
        <div className="App-container">
          <div className="row">
            <p>Selecione o tipo de banco para fazer a consulta </p>
          </div>
          <div>
            <select value={pathResource} onChange={handleOptionChange}>
              <option value="/relational-db">Relacional (MySQL)</option>
              <option value="/non-relational-db">
                Não Relacional (MongoDB)
              </option>
            </select>
          </div>
          <br />
          <div>
            <p>
              <strong>Recurso selecionado</strong>:{" "}
              <u style={{ color: "#06A9DE" }}>
                http://localhost:8081
                {pathResource}/products
              </u>
            </p>
          </div>

          <div>
            <Form.Label>
              Informa uma quantidade de usuários simultâneos para simulação:
            </Form.Label>
          </div>

          <div style={{ display: "inline-flex" }}>
            <Form.Control
              type="number"
              style={{ textAlign: "right" }}
              value={userQuantitySimulation}
              onChange={(e) => setUserQuantitySimulation(() => e.target.value)}
            />

            <Button
              disabled={showLoading}
              style={{ marginLeft: "8px" }}
              variant="secondary"
              onClick={handleSearchButtonClick}
            >
              Consultar
            </Button>
          </div>

          {showLoading && <Loading />}

          <div style={{ paddingTop: "16px" }}>
            <Table bordered style={{ fontSize: "12px", color: "white" }}>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Descrição</th>
                  <th>Preço</th>
                  <th>Categoria</th>
                  <th>Marca</th>
                  <th>Modelo</th>
                </tr>
              </thead>
              <tbody>
                {products.map((prod) => (
                  <tr key={prod.id}>
                    <td>{prod.id}</td>
                    <td>{prod.description}</td>
                    <td>
                      {new Intl.NumberFormat("pt-BR", {
                        style: "currency",
                        currency: "BRL",
                      }).format(prod.price)}
                    </td>
                    <td>{prod.category}</td>
                    <td>{prod.brand}</td>
                    <td>{prod.model}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </div>

          <div>
            <p>Tempo da última requisição: {metrics.lastTime} (s)</p>
            <p>Média de tempo de resposta: {metrics.avgTime} (s)</p>
            <p>Tempo total gasto: {metrics.totalTime} (s)</p>
            <p>Consumo de memória: {metrics.memoryUsage} (mb)</p>
            <p>Uso de processador: {metrics.cpuUsage} (clock /s)</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
