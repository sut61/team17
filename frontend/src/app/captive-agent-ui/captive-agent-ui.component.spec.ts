import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaptiveAgentUiComponent } from './captive-agent-ui.component';

describe('CaptiveAgentUiComponent', () => {
  let component: CaptiveAgentUiComponent;
  let fixture: ComponentFixture<CaptiveAgentUiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaptiveAgentUiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaptiveAgentUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
