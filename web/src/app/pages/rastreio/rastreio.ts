import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-rastreio',
  imports: [CommonModule, FormsModule, TranslateModule],
  templateUrl: './rastreio.html',
  styleUrl: './rastreio.scss',
})
export class Rastreio {
  codigoBusca = signal('');
  buscando = signal(false);
  resultado = signal<any>(null);

  buscarPacote() {
    if (this.codigoBusca().trim() === '') return;
    
    this.buscando.set(true);
    this.resultado.set(null);
    
    // Simula uma busca assíncrona
    setTimeout(() => {
      this.resultado.set({
        codigo: this.codigoBusca(),
        status: 'EM_TRANSITO',
        destinatario: 'Test ando',
        dataAtualizacao: new Date()
      });
      this.buscando.set(false);
    }, 1000);
  }
}
