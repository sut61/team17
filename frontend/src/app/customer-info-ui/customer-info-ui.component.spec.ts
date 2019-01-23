import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerInfoUIComponent } from './customer-info-ui.component';

describe('CustomerInfoUIComponent', () => {
  let component: CustomerInfoUIComponent;
  let fixture: ComponentFixture<CustomerInfoUIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerInfoUIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerInfoUIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
