
#### How it works?

The aim of this API is to manage a shopping cart of our ecommerce website. So, through it, the Frontend Team will be able to request and create, update or delete any item in the current cart.

Every action of the API will generate a new  _Block_  on the  _Blockchain_  in order to create a history and persist the information, so we can retrieve it later, but also keeps a history of which items the user has added to the cart

#### Workflow

The workflow of this API is as follows:

-   Create
    
    1.  Create request is received
        
    2.  Check if the  `id`  for a Cart is not already used
        
        1.  If it has been used, we return error
            
        2.  If it has not been used, we create the cart with the given item and generate a new Block to add to the Blockchain
            
    3.  Add the new  _Block_  to the  _Blockchain_
        
-   Update
    
    1.  Update request is received
        
    2.  Check if the  `id`  for a Cart exists
        
        1.  If not exists return error
            
        2.  If exist retrieve it
            
    3.  Update the items in the cart and generate a new Block with the information
        
    4.  Add the new  _Block_  to the  _Blockchain_
        
-   Delete
    

1.  Delete request is received
    
2.  Check if the  `id`  for a Cart exists
    
    1.  If not exists, return error
        
    2.  If exists retrieve it
        
3.  Remove cart information and create a new  _Block_
    
4.  Add the new  _Block_  to the  _Blockchain_
    

#### Technical Considerations

Keep in mind the following considerations related to the Blockchain:

1.  The blockchain has a first block called 'genesis block', these block doesn't contain a reference to the previous block, and usually is hard-coded on the software
    
2.  Every Block has multiple properties:
    
    1.  timestamp: the timestamp for the moment when the block was created
        
    2.  lastHash: hash of the previous block on the  _blockchain_
        
    3.  data: information we want to store in the block (in our case the votes)
        
    4.  nonce: a unique number
        
    5.  hash: a SHA256 string for the block, calculated concatenating the timestamp, lastHash, data, and nonce.
        
3.  The implementation of the Blockchain must follow these contracts:
    

```
        Blockchain
          /** Adds new block to blockchain */
          addBlock(block: Block): Block
          /** The new blockchain that is a candidate for replacing the current blockchain */
          replace(blockchain: Blockchain): boolean
          /**
           * Validates the chain by checking if:
           * - Genesis Block hash match given blockchain genesis block hash
           * - every element's last hash value matches previous block's hash
           * - data hasn't been tampered (which will produce a different hash value)
           */
          isValid(blockchain: Blockchain): boolean

        Block
          /** Generate the first block for the chain */
          static getGenesisBlock(): Block
          /** Generate the hash for the given block */
          static generateHashFromBlock(block: Block): string
        ```
```
