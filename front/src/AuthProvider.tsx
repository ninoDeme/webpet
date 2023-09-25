import {ParentComponent, createContext, onMount, useContext} from "solid-js";
import {createStore} from "solid-js/store";
import {User} from "./models/User";


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
  var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));

  console.log(jsonPayload);
  return JSON.parse(jsonPayload);
}

const authStore = createStore<User>(null);

export const AuthStore = () => {
  const [auth, setAuth] = authStore;

  onMount(() => {
    let jwt = localStorage.getItem("Auth");
    if (!jwt) return;
    console.log('onMount');
    setAuth(JSON.parse(jwt));
  });

  const updateAuth = (json: string) => {
    setAuth(() => JSON.parse(json));
    localStorage.setItem("Auth", json);
  };

  const checkAuth = function (): User {
    let jwt = localStorage.getItem("Auth");
    if (jwt) {
      setAuth(JSON.parse(jwt));
      return auth;
    }
    setAuth(() => null);
    return null;
  };

  return {auth, updateAuth, checkAuth};
};




const AuthContext = createContext<ReturnType<typeof AuthStore>>()

export const useAuth = () => useContext(AuthContext)!

export const AuthProvider: ParentComponent = (props) => {
  return <AuthContext.Provider value={AuthStore()}>{props.children}</AuthContext.Provider>
}
