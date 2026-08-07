import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { tap } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class AuthService{
    private http = inject(HttpClient);
    private apiUrl = 'http://localhost:8080/api/auth';

    login(dados: any){
        return this.http.post<{token: string}>(`${this.apiUrl}/login`, dados).pipe(
            tap(response => {
                localStorage.setItem('token', response.token);
            })
        );
    }

    obterToken(){
        return localStorage.getItem('token');
    }

    estaLogado(): boolean {
        return !!this.obterToken();
    }

    sair(){
        localStorage.removeItem('token');
    }
}