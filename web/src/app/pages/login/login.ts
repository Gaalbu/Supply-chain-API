import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private authService = inject(AuthService);
  private router = inject(Router);

  dadosLogin = signal({ login: '', password: ''})
  carregando = signal(false);
  erro = signal(false);

  fazerLogin(){
    if(!this.dadosLogin().login || !this.dadosLogin().password) return;
    
    this.carregando.set(true);
    this.erro.set(false);

    this.authService.login(this.dadosLogin()).subscribe({
      next: () => {
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.erro.set(true);
        this.carregando.set(false);
      }
    });
  }
}
