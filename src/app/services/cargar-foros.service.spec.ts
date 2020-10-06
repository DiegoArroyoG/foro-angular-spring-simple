import { TestBed } from '@angular/core/testing';

import { CargarForosService } from './cargar-foros.service';

describe('CargarForosService', () => {
  let service: CargarForosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CargarForosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
