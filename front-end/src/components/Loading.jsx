import { CircleNotch } from "phosphor-react";

export const Loading = () => {
  return (
    <div className="flex items-center justify-center overflow-hidden">
      <CircleNotch weight="bold">
        <animate
          attributeName="opacity"
          values="0;1;0"
          dur="4s"
          repeatCount="indefinite"
        ></animate>
        <animateTransform
          attributeName="transform"
          attributeType="XML"
          type="rotate"
          dur="5s"
          from="0 0 0"
          to="360 0 0"
          repeatCount="indefinite"
        ></animateTransform>
      </CircleNotch>
    </div>
  );
};
