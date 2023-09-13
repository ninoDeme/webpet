#!/usr/bin/env python3
import os
import pandas as pd
import sqlite3

connection = sqlite3.connect('banco.db')
categorias: set[str] = set()
animais: set[str] = set()
produtos = []

df = pd.read_csv('dados.csv')
categorias.update(set(df['Produtos'].to_list()))
animais.update(set(df['Animais'].to_list()))
for _, produto in df.iterrows():
    index = 0
    for c in categorias:
        if c == produto["Produtos"]:
            catP = index
        index += 1
    index = 0
    for a in animais:
        if a == produto['Animais']:
            aniP = index
        index += 1
    produtos.append({
        'nome': str(produto['Nome']).replace("'", "''"),
        'categoria': catP,
        'animal': aniP,
        'preco': str(produto['Preço']).replace(',', '.'),
        'peso': str(produto['Peso']).replace("'", "''"),
        'descricao': str(produto['Descrição']).replace("'", "''"),
        'detalhes': str(produto['Especificações']).replace("'", "''"),
        'imagem': str(produto['Imagem']).replace("'", "''"),
    })

categoriasSQL = 'INSERT INTO categoria (nome) VALUES '
for i in categorias:
    categoriasSQL += f"('{i}'),"
categoriasSQL = categoriasSQL.removesuffix(',') + ';'
connection.execute(categoriasSQL)

animaisSQL = 'INSERT INTO animal (nome) VALUES '
for i in animais:
    animaisSQL += f"('{i}'),"
animaisSQL = animaisSQL.removesuffix(',') + ';'
connection.execute(animaisSQL)

produtosSQL = """INSERT INTO produto (
    nome,
    descricao,
    detalhes,
    peso,
    preco,
    quantidade,
    imagem,
    id_animal,
    id_categoria
) values """
for p in produtos:
    produtosSQL += f"('{p['nome']}', '{p['descricao']}', '{p['detalhes']}', '{p['peso']}', {p['preco']}, 0, {p['imagem']}, {p['animal']}, {p['categoria']}),"
produtosSQL = produtosSQL.removesuffix(',') + ';'
connection.execute(produtosSQL)
connection.commit()
connection.close()
