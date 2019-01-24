import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarserviceUiComponent } from './carservice-ui.component';

describe('CarserviceUiComponent', () => {
  let component: CarserviceUiComponent;
  let fixture: ComponentFixture<CarserviceUiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarserviceUiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarserviceUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
