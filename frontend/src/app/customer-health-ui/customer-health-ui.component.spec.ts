import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerHealthUIComponent } from './customer-health-ui.component';

describe('CustomerHealthUIComponent', () => {
  let component: CustomerHealthUIComponent;
  let fixture: ComponentFixture<CustomerHealthUIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerHealthUIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerHealthUIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
