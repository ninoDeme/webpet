import { Component } from "solid-js";
import Mensagem from "../templates/mensagem";
import { useNavigate } from "@solidjs/router";

const CadastroSucesso: Component = () => {
  const text = "Cadastro realizado com sucesso! Faça o seu login";
  const navigate = useNavigate();
  return <Mensagem text={text} onClick={() => navigate("/login")} button="Fazer Login"/>
}

export default CadastroSucesso;
