/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        fundo_escuro: '#181747',
        fundo: '#FFF2E2',
        fundo_alt: '#97c3b7',
        fundo1: '#9da7c8',
        fundo2: '#8292c1',
        fundo3: '#4f75e0',
        texto: '#111111',
        texto_claro: '#97c3b7',
      }
    },
  },
  plugins: [],
}

