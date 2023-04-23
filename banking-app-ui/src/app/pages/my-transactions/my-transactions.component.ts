import { Component, OnInit } from '@angular/core';
import { TransactionsService } from '../../services/services/transactions.service';
import { TransactionDto } from '../../services/models/transaction-dto';
import { HelperService } from '../../services/helper/helper.service';

@Component({
  selector: 'app-my-transactions',
  templateUrl: './my-transactions.component.html',
  styleUrls: ['./my-transactions.component.scss']
})
export class MyTransactionsComponent implements OnInit {

  transactions: Array<TransactionDto> = [];

  constructor(
    private transactionService: TransactionsService,
    private helperSevice: HelperService
  ) { }

  ngOnInit(): void {
    this.transactionService.findAllByUserId({
      'user-id': this.helperSevice.userId
    }).subscribe({
      next: (data) => {
        this.transactions = data;
        // @ts-ignore
        this.transactions.sort((a, b) => new Date(b.transactionDate).getTime() - new Date(a.transactionDate).getTime())
      }
    });
  }

}
