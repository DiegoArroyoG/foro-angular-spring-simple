import { TestBed } from '@angular/core/testing';

import { GestorForosService } from './gestor-foros.service';

describe('CargarForosService', () => {
  let service: GestorForosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestorForosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
