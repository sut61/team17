import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyPolycyUiComponent } from './property-polycy-ui.component';

describe('PropertyPolycyUiComponent', () => {
  let component: PropertyPolycyUiComponent;
  let fixture: ComponentFixture<PropertyPolycyUiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PropertyPolycyUiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyPolycyUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
