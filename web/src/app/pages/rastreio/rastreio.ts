import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { Pacote, PacoteService } from '../../services/pacote.service';

@Component({
  selector: 'app-rastreio',
  imports: [CommonModule, FormsModule, TranslateModule],
  templateUrl: './rastreio.html',
  styleUrl: './rastreio.scss',
})
export class Rastreio {
  private readonly pacoteService = inject(PacoteService);

  codigoBusca = signal('');
  buscando = signal(false);
  resultado = signal<Pacote | null>(null);
  naoEncontrado = signal(false);
  erro = signal(false);

  buscarPacote() {
    if (this.codigoBusca().trim() === '') return;
    
    const codigo = this.codigoBusca().trim();
    this.buscando.set(true);
    this.resultado.set(null);
    this.naoEncontrado.set(false);
    this.erro.set(false);

    this.pacoteService.buscarPorCodigo(codigo).subscribe({
      next: (pacote) => {
        this.resultado.set(pacote);
        this.buscando.set(false);
      },
      error: (response) => {
        this.naoEncontrado.set(response.status === 404);
        this.erro.set(response.status !== 404);
        this.buscando.set(false);
      },
    });
  }
}
