import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErdlmNavBarComponent } from './erdlm-nav-bar.component';

describe('ErdlmNavBarComponent', () => {
  let component: ErdlmNavBarComponent;
  let fixture: ComponentFixture<ErdlmNavBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErdlmNavBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ErdlmNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
