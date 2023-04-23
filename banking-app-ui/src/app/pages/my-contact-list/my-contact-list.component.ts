import { Component, OnInit } from '@angular/core';
import { ContactService } from '../../services/services/contact.service';
import { ContactDto } from '../../services/models/contact-dto';
import { HelperService } from '../../services/helper/helper.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-contact-list',
  templateUrl: './my-contact-list.component.html',
  styleUrls: ['./my-contact-list.component.scss']
})
export class MyContactListComponent implements OnInit {

  contacts: Array<ContactDto> = [];
  userIdToDelete: any = -1;
  constructor(
    private contactService: ContactService,
    private helperService: HelperService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.findAllContactByUser();
  }

  private findAllContactByUser() {
    this.contactService.findAllByUserId1({
      'user-id': this.helperService.userId
    }).subscribe({
      next: (data) => {
        this.contacts = data;
      }
    });
  }

  async update(id: number | undefined) {
    await this.router.navigate(['user','new-contact', id]);
  }

  delete() {
    if(this.userIdToDelete) {
      this.contactService.delete2({
        'contact-id': this.userIdToDelete
      }).subscribe({
        next: () => {
          this.findAllContactByUser();
        }
      });
    }
  }
}
