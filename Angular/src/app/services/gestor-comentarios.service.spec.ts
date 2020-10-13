import { TestBed } from '@angular/core/testing';

import { GestorComentariosService } from './gestor-comentarios.service';

describe('GestorComentariosService', () => {
  let service: GestorComentariosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestorComentariosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
