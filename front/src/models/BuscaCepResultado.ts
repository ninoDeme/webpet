export type BuscaCepResultado = {
    "status": number,
    "code": string,
    "state": string,
    "city": string,
    "district": string,
    "address": string;
} | string;