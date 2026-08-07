import { Component, signal, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TranslateModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  private readonly translate = inject(TranslateService);

  protected readonly title = signal('supply-chain-web');
  protected readonly currentLang = signal('pt-br');

  constructor() {
    const savedLang = localStorage.getItem('lang') || 'pt-br';
    this.currentLang.set(savedLang);
    this.translate.setDefaultLang('pt-br');
    this.translate.use(savedLang);
  }

  protected toggleLang() {
    const nextLang = this.currentLang() === 'pt-br' ? 'en-us' : 'pt-br';
    this.currentLang.set(nextLang);
    this.translate.use(nextLang);
    localStorage.setItem('lang', nextLang);
  }
}
