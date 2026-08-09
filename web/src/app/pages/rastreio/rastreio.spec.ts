import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideTranslateService } from '@ngx-translate/core';
import { of, throwError } from 'rxjs';

import { Pacote, PacoteService } from '../../services/pacote.service';
import { Rastreio } from './rastreio';

describe('Rastreio', () => {
  let component: Rastreio;
  let fixture: ComponentFixture<Rastreio>;
  let service: { buscarPorCodigo: ReturnType<typeof vi.fn> };

  const pacote: Pacote = {
    id: 1,
    codigo: 'PKG-001',
    destinatario: 'Destinatário',
    status: 'EM_TRANSITO',
    dataAtualizacao: '2026-08-08T12:00:00',
  };

  beforeEach(async () => {
    service = { buscarPorCodigo: vi.fn() };
    await TestBed.configureTestingModule({
      imports: [Rastreio],
      providers: [
        provideTranslateService(),
        { provide: PacoteService, useValue: service },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(Rastreio);
    component = fixture.componentInstance;
  });

  it('renders a package returned by the API', () => {
    service.buscarPorCodigo.mockReturnValue(of(pacote));
    component.codigoBusca.set(' PKG-001 ');

    component.buscarPacote();
    fixture.detectChanges();

    expect(service.buscarPorCodigo).toHaveBeenCalledWith('PKG-001');
    expect(component.resultado()).toEqual(pacote);
    expect(component.buscando()).toBe(false);
    expect(fixture.nativeElement.textContent).toContain('Destinatário');
  });

  it('distinguishes a missing package from a network error', () => {
    service.buscarPorCodigo.mockReturnValue(throwError(() => ({ status: 404 })));
    component.codigoBusca.set('UNKNOWN');

    component.buscarPacote();

    expect(component.naoEncontrado()).toBe(true);
    expect(component.erro()).toBe(false);
  });
});
