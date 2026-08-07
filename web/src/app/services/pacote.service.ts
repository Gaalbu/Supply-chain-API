import { inject, Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

export interface Pacote {
    id?: string;
    codigo: string;
    destinatario: string;
    status: string;
    dataAtualizacao: string;
}

@Injectable({
    providedIn: 'root'
})
export class PacoteService{
    private http = inject(HttpClient);
    private apiUrl = 'http://localhost:8080/api/pacotes';

    listarTodos(): Observable<Pacote[]> {
        return this.http.get<Pacote[]>(this.apiUrl);
    }

    atualizarStatus(id: string, novoStatus: string): Observable<Pacote> {
    return this.http.put<Pacote>(`${this.apiUrl}/${id}/status`, { status: novoStatus });
    }
}

