import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/services/user.service';
import { UserDto } from '../../services/models/user-dto';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.scss']
})
export class ManageUsersComponent implements OnInit {

  customers: Array<UserDto> = [];
  showInactiveUserOnly = false;
  userIdToUpdate = -1;
  updateState: boolean | undefined;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.findAllCustomers();
  }

  private findAllCustomers() {
    this.userService.findAll()
    .subscribe({
      next: (value) => {
        this.customers = value;
      }
    });
  }

  filterCustomers() {
    if (this.showInactiveUserOnly) {
      this.customers = this.customers.filter((c) => !c.active);
    } else {
      this.findAllCustomers();
    }
  }

  changeUserState(active: boolean | undefined, id: number | undefined) {
    this.userIdToUpdate = id as number;
    this.updateState = active;
  }

  updateUserState() {
    if (this.updateState) {
      this.userService.validateAccount({
        'user-id': this.userIdToUpdate as number
      }).subscribe({
        next: () =>{
          this.findAllCustomers();
        }
      });
    } else {
      this.userService.invalidateAccount({
        'user-id': this.userIdToUpdate as number
      }).subscribe({
        next: () =>{}
      });
    }
  }

  cancelUpdate() {
    const user = this.customers.find((c) =>c.id === this.userIdToUpdate);
    if (user) {
      user.active = !user.active
    }
    this.userIdToUpdate = -1;
    this.updateState = undefined
  }
}
