import { inject, Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { environment } from '../../environments/environment';

export interface Pacote {
    id: number;
    codigo: string;
    destinatario: string;
    status: string;
    dataAtualizacao: string | null;
}

@Injectable({
    providedIn: 'root'
})
export class PacoteService{
    private http = inject(HttpClient);
    private readonly apiUrl = `${environment.apiUrl}/pacotes`;

    listarTodos(): Observable<Pacote[]> {
        return this.http.get<Pacote[]>(this.apiUrl);
    }

    buscarPorCodigo(codigoRastreio: string): Observable<Pacote> {
        return this.http.get<Pacote>(`${this.apiUrl}/${encodeURIComponent(codigoRastreio)}`);
    }

    atualizarStatus(codigoRastreio: string, novoStatus: string): Observable<void> {
        return this.http.patch<void>(`${this.apiUrl}/${encodeURIComponent(codigoRastreio)}/status`, { novoStatus });
    }
}
