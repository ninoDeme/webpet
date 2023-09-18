import { createSignal } from "solid-js";
import { User } from "./models/user";

function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function parseJwt(token) {
  var base64Url = token.split('.')[1];
  var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));

  console.log(jsonPayload)
  return JSON.parse(jsonPayload);
}

export const authSignal = createSignal<User>();
export const checkAuth = function(): User {
  let jwt = localStorage.getItem("Auth")
  if (jwt) {
    
    authSignal[1](parseJwt(jwt));
    return authSignal[0]();
  }
}

export const setAuth = function(jwt: string) {
  localStorage.setItem("Auth", jwt);
  checkAuth();
}

export const favoritar = async (id: number) => {
  const res = await fetch("http://localhost:9000/favoritos?id=" + id + "&u=" + checkAuth().id, {mode: "cors"})

  if (res.status === 200) {
    return true;
  } else {
    throw new Error(await res.text());
  }
}
