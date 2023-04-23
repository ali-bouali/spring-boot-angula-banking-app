export interface TransactionDto {
  id?: number;
  amount?: number;
  type?: string;
  destinationIban?: string;
  transactionDate?: string;
  userId?: number;
}
