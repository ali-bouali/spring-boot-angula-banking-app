import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LightInfoComponent } from './light-info.component';

describe('LightInfoComponent', () => {
  let component: LightInfoComponent;
  let fixture: ComponentFixture<LightInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LightInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LightInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
