import { TestBed } from '@angular/core/testing';

import { FirstService } from './first.service';

describe('FirstServiceService', () => {
  let service: FirstService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirstService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
