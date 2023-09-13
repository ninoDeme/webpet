import type { Config } from 'tailwindcss';

const config: Config = {
  content: [
    './index.html',
    './src/**/*.{js,ts,jsx,tsx,css,md,mdx,html,json,scss}',
  ],
  darkMode: 'class',
  theme: {
    extend: {
      colors: {
        fundo_escuro: '#181747',
        fundo: '#fff2e2',
        fundo_alt: '#97c3b7',
        fundo1: '#9da7c8',
        fundo2: '#8292c1',
        fundo3: '#4f75e0',
        texto: '#111111',
        texto_claro: '#97c3b7',
      }
    }
  },
  plugins: [],
};

export default config;
