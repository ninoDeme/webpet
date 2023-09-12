#!/usr/bin/env python3
import pandas as pd

ID_PLANILHA = '17gTd6GbgJpuq4znzCQOJU1X308swCufyYC1wJNtj4XM'

planilhas = ['Cachorro', 'Gato', 'Passaros', 'Peixes', 'Outros']

dados = None
for nome_planilha in planilhas:
    url = f'https://docs.google.com/spreadsheets/d/{ID_PLANILHA}/gviz/tq?tqx=out:csv&sheet={nome_planilha}'
    df = pd.read_csv(url)
    if type(dados) == type(None):
        dados = df
    else:
        dados = pd.concat([dados, df], axis = 0)

if type(dados) != type(None):
    dados.to_csv('dados.csv')
