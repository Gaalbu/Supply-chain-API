import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Rastreio } from './rastreio';

describe('Rastreio', () => {
  let component: Rastreio;
  let fixture: ComponentFixture<Rastreio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Rastreio],
    }).compileComponents();

    fixture = TestBed.createComponent(Rastreio);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
