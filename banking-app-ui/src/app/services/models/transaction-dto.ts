/* tslint:disable */
/* eslint-disable */
export interface TransactionDto {
  amount?: number;
  destinationIban?: string;
  id?: number;
  transactionDate?: string;
  type?: 'TRANSFERT' | 'DEPOSIT';
  userId?: number;
}
