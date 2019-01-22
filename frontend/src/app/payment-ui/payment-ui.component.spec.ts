import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentUIComponent } from './payment-ui.component';

describe('PaymentUIComponent', () => {
  let component: PaymentUIComponent;
  let fixture: ComponentFixture<PaymentUIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentUIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentUIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
