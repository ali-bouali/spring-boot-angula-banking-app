import { TestBed } from '@angular/core/testing';

import { TokenGuardService } from './token-guard.service';

describe('TokenGuardService', () => {
  let service: TokenGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TokenGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
