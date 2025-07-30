import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovingComponent } from './approving.component';

describe('ApprovingComponent', () => {
  let component: ApprovingComponent;
  let fixture: ComponentFixture<ApprovingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApprovingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprovingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
