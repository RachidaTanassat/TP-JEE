import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  public products: Array<Product>=[];
  public keyword: string="";

  constructor(private productService: ProductService) {
  }
  private getProducts() {
     this.productService.getProducts(1 ,3).subscribe({
        next: (resp) => {
          this.products = resp.body as Product[];
          const totalCount = resp.headers;
          console.log(totalCount);

        },
        error: error=> {
      console.log(error);
        }
      })
    //this.products = this.productService.getProducts();
  }
  handleCheckProduct(product: Product) {
    this.productService.checkProducts(product).subscribe({
      next: updatedProduct => {
        product.checked = !product.checked;
      }
    })
  }

  ngOnInit(): void {

    this.getProducts();
  }

  handleDelete(product: Product) {
    if(confirm("Etes-vous sure?"))

      this.productService.deleteProduct(product).subscribe({
        next: value => {
         // this.getProducts();
          this.products=this.products.filter(p=>p.id!=product.id)
        }
      })

  }


  searchProducts() {
    this.productService.searchProducts(this.keyword).subscribe({
      next: value => {
        this.products = value;

      }

    })

  }
}
