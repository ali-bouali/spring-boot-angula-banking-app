import { Component, OnInit } from '@angular/core';
import { StatisticsService } from '../../services/services/statistics.service';
import { ContactService } from '../../services/services/contact.service';
import { TransactionsService } from '../../services/services/transactions.service';
import { HelperService } from '../../services/helper/helper.service';
import { Router } from '@angular/router';
import { TransactionDto } from '../../services/models/transaction-dto';
import { ContactDto } from '../../services/models/contact-dto';

@Component({
  selector: 'app-new-transaction',
  templateUrl: './new-transaction.component.html',
  styleUrls: ['./new-transaction.component.scss']
})
export class NewTransactionComponent implements OnInit {

  transaction: TransactionDto = {};
  contacts: Array<ContactDto> = [];
  accountBalance = 0;
  errorMessages: Array<string> = [];

  constructor(
    private statisticService: StatisticsService,
    private contactService: ContactService,
    private transactionService: TransactionsService,
    private helperService: HelperService,
    private router: Router

  ) { }

  ngOnInit(): void {
    this.findAllContactByUser();
    this.statisticService.getAccountBalance({
      'user-id': this.helperService.userId
    }).subscribe({
      next: (data) => {
        this.accountBalance = data;
      }
    })
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

  async cancel() {
    await this.router.navigate(['user', 'my-transactions']);
  }

  save() {
    this.errorMessages = [];
    this.transaction.userId = this.helperService.userId;
    this.transactionService.save1({
      body: this.transaction
    }).subscribe({
      next: async () => {
        await this.router.navigate(['user', 'my-transactions']);
      },
      error: (err) => {
        this.errorMessages = err.error.validationErrors;
      }
    });
  }
}
