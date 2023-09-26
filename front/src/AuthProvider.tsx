import {ParentComponent, createContext, createSignal, onMount, useContext} from "solid-js";
import {User} from "./models/User";

const authStore = createSignal<User>(null);

export const AuthStore = () => {
  const [auth, setAuth] = authStore;

  const checkAuth = async function (): Promise<User> {
    const res = await fetch("http://localhost:3000/api/login");
    if (res.status === 200) {
      updateAuth(await res.text());
    } else {
      updateAuth(null);
    }
    return auth();
  };

  onMount(() => {
    let jwt = localStorage.getItem("Auth");
    if (jwt) {
      setAuth(JSON.parse(jwt));
    };
    checkAuth();
  });

  const updateAuth = (json: string) => {
    setAuth(() => JSON.parse(json));
    localStorage.setItem("Auth", json);
  };

  const logout = async function () {
    const res = await fetch("http://localhost:3000/api/logout")
    if (!res.ok) {
      console.error((res.text()));
    } else {
      localStorage.removeItem("Auth");
      setAuth(() => undefined);
    }
  };

  return {auth, updateAuth, checkAuth, logout};
};

const AuthContext = createContext<ReturnType<typeof AuthStore>>();

export const useAuth = () => useContext(AuthContext)!;

export const AuthProvider: ParentComponent = (props) => {
  return <AuthContext.Provider value={AuthStore()}>{props.children}</AuthContext.Provider>;
};
