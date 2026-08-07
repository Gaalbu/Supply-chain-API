import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { Pacote, PacoteService } from '../../services/pacote.service';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard implements OnInit{
  private pacoteService = inject(PacoteService);

  pacotes = signal<Pacote[]> ([]);
  carregando = signal(true);
  erro = signal(false);
  
  ngOnInit() {
    this.carregarPacotes();
  }

  carregarPacotes() {
    this.carregando.set(true);
    this.erro.set(false);

    this.pacoteService.listarTodos().subscribe({
      next: (dadosDaApi) => {
        this.pacotes.set(dadosDaApi);
        this.carregando.set(false);
      },
      error: (err) => {
        console.error('Erro ao buscar pacotes:', err);
        this.erro.set(true);
        this.carregando.set(false);
      }
    });
  }
}
