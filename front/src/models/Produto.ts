import {AnimalI} from './Animal';
import {CategoriaI} from './Categoria';

export interface ProdutoSimplesI {
    id: number;
    nome: string;
    preco: number;
    quantidade: number;
    imagem: string;
}

export interface ProdutoI extends ProdutoSimplesI {
    detalhes: string;
    descricao: string;
    peso: string;
    animal: AnimalI;
    categoria: CategoriaI;
}