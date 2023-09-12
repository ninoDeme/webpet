#!/usr/bin/env python3
import os
import pandas as pd
import sqlite3

connection = sqlite3.connect('banco.db')
categorias = set()
animais = set()
produtos = []
for i in os.listdir('dados'):
    df = pd.read_csv(f'dados/{i}')
    categorias.update(set(df['Produtos'].to_list()))
    animais.update(set(df['Animais'].to_list()))
    produtos.extend(df.iterrows())


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

connection.commit()
connection.close()
    