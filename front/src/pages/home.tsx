import { Component } from "solid-js";
import banner1 from "/banner1.webp";
import banner2 from "/banner2.webp";
import banner3 from "/banner3.webp";
import banner4 from "/banner4.webp";

const Home: Component = () => {
  return (
    <>
    <div class="w-full flex justify-stretch overflow-clip h-72 mt-2">
      <img class="flex-1 object-cover" src={banner1}/>
      <img class="flex-1 object-cover" src={banner2}/>
      <img class="flex-1 object-cover" src={banner3}/>
      <img class="flex-1 object-cover" src={banner4}/>
    </div>
    </>
    )
}

export default Home
