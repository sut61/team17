import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarDataUiComponent } from './car-data-ui.component';

describe('CarDataUiComponent', () => {
  let component: CarDataUiComponent;
  let fixture: ComponentFixture<CarDataUiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarDataUiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarDataUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
