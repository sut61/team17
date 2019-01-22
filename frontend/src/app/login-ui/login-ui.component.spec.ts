import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginUIComponent } from './login-ui.component';

describe('LoginUIComponent', () => {
  let component: LoginUIComponent;
  let fixture: ComponentFixture<LoginUIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginUIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginUIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
