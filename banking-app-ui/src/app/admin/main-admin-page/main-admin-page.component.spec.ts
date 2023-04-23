import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainAdminPageComponent } from './main-admin-page.component';

describe('MainAdminPageComponent', () => {
  let component: MainAdminPageComponent;
  let fixture: ComponentFixture<MainAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainAdminPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MainAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
