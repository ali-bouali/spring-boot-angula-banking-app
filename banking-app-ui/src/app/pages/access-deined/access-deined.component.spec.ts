import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccessDeinedComponent } from './access-deined.component';

describe('AccessDeinedComponent', () => {
  let component: AccessDeinedComponent;
  let fixture: ComponentFixture<AccessDeinedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccessDeinedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccessDeinedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
