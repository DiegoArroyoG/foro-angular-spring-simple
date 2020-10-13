import { TestBed } from '@angular/core/testing';

import { GestorTemasService } from './gestor-temas.service';

describe('GestorTemasService', () => {
  let service: GestorTemasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestorTemasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
