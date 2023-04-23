import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/services/user.service';
import { HelperService } from '../../services/helper/helper.service';
import { UserDto } from '../../services/models/user-dto';
import { Address } from 'cluster';
import { AddressDto } from '../../services/models/address-dto';
import { AddressService } from '../../services/services/address.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userDto: UserDto = {
    email: '',
    firstname: '',
    lastname: '',
    password: ''
  };

  successMsg = '';

  constructor(
    private userService: UserService,
    private helperService: HelperService
  ) { }

  ngOnInit(): void {
    this.userService.findById({
      'user-id': this.helperService.userId
    }).subscribe({
      next: (data) => {
        this.userDto = data;
      }
    });
  }

  save() {
    this.successMsg = '';
    this.userDto.id = this.helperService.userId;
    this.userService.save({
      body: this.userDto
    }).subscribe({
      next: () => {
        this.successMsg = 'Your profile has been successfully updated';
      }
    });
  }
}
