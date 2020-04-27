# Flight Ticket Api

Projemiz, uçuşlar oluşturan ve bu uçuşlara ait bilet alınabilmesini sağlayan api'leri barındırır. Projeyi build ettikten sonra tarayiciniz ile 'http://localhost:8080/swagger-ui.html' adresine gidip api'ye ait tüm servisleri görebilir ve bu servislere request atıp, response'larini alabilirsiniz. 

# Projede Kullanılan Teknolojiler 

- Projemiz Spring Boot Mvc projesidir.
- Loglama için Aop kullanilmistir.
- Rest Api'mizi dökümante edebilmek amaci ile swagger kullanilmistir.
- ORM aracı olarak Spring Data Jpa kullanilmistir.
- Db icin H2 Database kullanilmisitir.
- Ekstra olarak projenin build edilmesi icin Dockerfile eklenmiştir.
- Junit
- Lombok
- Maven

# Projenin Build Edilmesi

Projenin İntellij idea araciliyla build islemi:

  Projeyi Github'dan bilgisayarimizi indirdikten sonra İntellij idea acilir ve File->New->Module From Existing Source tıklanir ve projenin   oldugu dizin gosterilir. Maven secilir ve next'e basilarak proje eklenmis olur. Edit configuration acilir ve Spring Boot secilir main class olarak TicketApplication.java class' inin oldugu path verilir. JRE olarakta 1.8 secilir ve run edilir.
  
Projenin Docker araciliyla build islemi:  

  Asagidaki iki komut calistirilir ve projemizin build olaracaktir.
  - docker build -f Dockerfile -t ticket .
  - docker run -p 8080:8080 ticket
  

# Code Coverage

    Overall Coverage Summary
    Package	                                Class, %	        Method, %	        Line, %
    all classes	                        100% (35/ 35)	        89.8% (114/ 127)	90.1% (192/ 213)
    
    Coverage Breakdown
    Package	                                Class, %	        Method, %	        Line, %
    com.ticket.ticket	                100% (1/ 1)	        50% (1/ 2)	        33.3% (1/ 3)
    com.ticket.ticket.config.logging	100% (1/ 1)	        50% (2/ 4)	        50% (9/ 18)
    com.ticket.ticket.config.mvc	        100% (1/ 1)	        100% (4/ 4)	        100% (16/ 16)
    com.ticket.ticket.config.swagger	100% (1/ 1)	        100% (3/ 3)	        100% (6/ 6)
    com.ticket.ticket.controller	        100% (6/ 6)	        94.1% (16/ 17)	        94.1% (16/ 17)
    com.ticket.ticket.entity	        100% (5/ 5)	        85.2% (23/ 27)	        85.2% (23/ 27)
    com.ticket.ticket.filter	        100% (5/ 5)	        100% (17/ 17)	        100% (17/ 17)
    com.ticket.ticket.service	        100% (5/ 5)	        100% (21/ 21)	        100% (43/ 43)
    com.ticket.ticket.service.base	        100% (3/ 3)	        100% (8/ 8)	        100% (17/ 17)
    com.ticket.ticket.specification	        100% (5/ 5)	        75% (15/ 20)    	88.4% (38/ 43)
    com.ticket.ticket.specification.base	100% (2/ 2)	        100% (4/ 4)	        100% (6/ 6)
  

# Servisler

    Airline
    
    * http://localhost:8080/airlines (POST) -> Sisteme airline eklenmesini sağlar.
    
        * Request -> {
                       "id": null,
                       "name": "Turk Hava Yolları"
                     }
                     
        * Response -> {
                        "id": 1,
                        "name": "Turk Hava Yolları"
                      }              
    
    * http://localhost:8080/airlines (GET) -> Sistemde var olan airline'ları getirir.
        
        * Response -> [
                        {
                          "id": 1,
                          "name": "Turk Hava Yolları"
                        },
                        {
                          "id": 2,
                          "name": "Pegasus"
                        }
                      ]
                      
    * http://localhost:8080/airlines/filter?name=pegasus (POST) -> Sisteme olan airline'ların ismine göre arama yapar.
                         
          * Response -> [
                          {
                            "id": 2,
                            "name": "Pegasus"
                          }
                        ]      
                            
    Airport
    
        * http://localhost:8080/airports (POST) -> Sisteme airport eklenmesini sağlar.
        
            * Request -> {
                           "id": null,
                           "name": "İstanbul Havalimanı"
                         }
                         
            * Response -> {
                            "id": 3,
                            "name": "İstanbul Havalimanı"
                          }              
        
        * http://localhost:8080/airports (GET) -> Sistemde var olan airport'ları getirir.
            
            * Response -> [
                            {
                              "id": 3,
                              "name": "İstanbul Havalimanı"
                            },
                            {
                              "id": 4,
                              "name": "Eskişehir Havalimanı"
                            }
                          ]
                          
        * http://localhost:8080/airports/filter?name=es (POST) -> Sisteme olan airport'ların ismine göre arama yapar.
                             
              * Response -> [
                              {
                                "id": 4,
                                "name": "Eskişehir Havalimanı"
                              }
                            ]       
    
    Route
        
            * http://localhost:8080/routes (POST) -> Sisteme route eklenmesini sağlar.
            
                * Request -> {
                               "airport": {
                                 "id": 4,
                                 "name": "Eskişehir Havalimanı"
                               },
                               "id": null,
                               "name": "Eskişehir Route"
                             }
                             
                * Response -> {
                                "id": 5,
                                "name": "Eskişehir Route",
                                "airport": {
                                  "id": 4,
                                  "name": "Eskişehir Havalimanı"
                                }
                              }             
            
            * http://localhost:8080/routes (GET) -> Sistemde var olan route'ları getirir.
                
                * Response -> [
                                {
                                  "id": 5,
                                  "name": "Eskişehir Route",
                                  "airport": {
                                    "id": 4,
                                    "name": "Eskişehir Havalimanı"
                                  }
                                },
                                {
                                  "id": 6,
                                  "name": "İstanbul Route",
                                  "airport": {
                                    "id": 3,
                                    "name": "İstanbul Havalimanı"
                                  }
                                }
                              ]
                              
            * http://localhost:8080/routes/filter?name=es (POST) -> Sisteme olan route'ların ismine göre arama yapar.
                                 
                  * Response -> [
                                  {
                                    "id": 5,
                                    "name": "Eskişehir Route",
                                    "airport": {
                                      "id": 4,
                                      "name": "Eskişehir Havalimanı"
                                    }
                                  }
                                ]              
                                
    Flight
            
                * http://localhost:8080/flights (POST) -> Sisteme flight eklenmesini sağlar.
                
                    * Request -> {
                                   "airline": {
                                     "id": 1,
                                     "name": "Turk Hava Yolları"
                                   },
                                   "capacity": 10,
                                   "id": null,
                                   "name": "Turk Hava Yolları İstanbul Uçuşu",
                                   "price": 10,
                                   "route": {
                                     "id": 6,
                                     "name": "İstanbul Route",
                                     "airport": {
                                       "id": 3,
                                       "name": "İstanbul Havalimanı"
                                     }
                                   }
                                 }
                                 
                    * Response -> {
                                    "id": 7,
                                    "name": "Turk Hava Yolları İstanbul Uçuşu",
                                    "capacity": 10,
                                    "price": 10,
                                    "airline": {
                                      "id": 1,
                                      "name": "Turk Hava Yolları"
                                    },
                                    "route": {
                                      "id": 6,
                                      "name": "İstanbul Route",
                                      "airport": {
                                        "id": 3,
                                        "name": "İstanbul Havalimanı"
                                      }
                                    }
                                  }             
                
                * http://localhost:8080/flights (GET) -> Sistemde var olan flight'ları getirir.
                    
                    * Response -> [
                                    {
                                      "id": 7,
                                      "name": "Turk Hava Yolları İstanbul Uçuşu",
                                      "capacity": 10,
                                      "price": 10,
                                      "airline": {
                                        "id": 1,
                                        "name": "Turk Hava Yolları"
                                      },
                                      "route": {
                                        "id": 6,
                                        "name": "İstanbul Route",
                                        "airport": {
                                          "id": 3,
                                          "name": "İstanbul Havalimanı"
                                        }
                                      }
                                    },
                                    {
                                      "id": 8,
                                      "name": "Pegasus Eskişehir Uçuşu",
                                      "capacity": 20,
                                      "price": 20,
                                      "airline": {
                                        "id": 2,
                                        "name": "Pegasus"
                                      },
                                      "route": {
                                        "id": 5,
                                        "name": "Eskişehir Route",
                                        "airport": {
                                          "id": 4,
                                          "name": "Eskişehir Havalimanı"
                                        }
                                      }
                                    }
                                  ]
                                  
                * http://localhost:8080/flights/filter?airlineName=pe&name=es (POST) -> Sisteme olan flight'ların hem
                    ismine göre hemde uçuşa ait airline'ın ismine göre arama yapar.
                                     
                      * Response -> [
                                      {
                                        "id": 8,
                                        "name": "Pegasus Eskişehir Uçuşu",
                                        "capacity": 20,
                                        "price": 20,
                                        "airline": {
                                          "id": 2,
                                          "name": "Pegasus"
                                        },
                                        "route": {
                                          "id": 5,
                                          "name": "Eskişehir Route",
                                          "airport": {
                                            "id": 4,
                                            "name": "Eskişehir Havalimanı"
                                          }
                                        }
                                      }
                                    ]      
                                    
    Ticket
    
                * http://localhost:8080/tickets/available (GET) -> Sistemde var olan uçuşlara ait ticket'ları getirir. 
                    Kapasitesi dolan uçuşların ticket'larını getirmez. Ayrıca uçuş kapasitesi %10-%20-%30.. gibi
                    doluluk oranına ulaştığında her %10 artış için bilet fiyatına %10 zam yapar.
                                    
                    * Response -> [
                                    {
                                      "price": 10,
                                      "number": 1864,
                                      "flight": {
                                        "id": 7,
                                        "name": "Turk Hava Yolları İstanbul Uçuşu",
                                        "capacity": 10,
                                        "price": 10,
                                        "airline": {
                                          "id": 1,
                                          "name": "Turk Hava Yolları"
                                        },
                                        "route": {
                                          "id": 6,
                                          "name": "İstanbul Route",
                                          "airport": {
                                            "id": 3,
                                            "name": "İstanbul Havalimanı"
                                          }
                                        }
                                      }
                                    },
                                    {
                                      "price": 20,
                                      "number": 5350,
                                      "flight": {
                                        "id": 8,
                                        "name": "Pegasus Eskişehir Uçuşu",
                                        "capacity": 20,
                                        "price": 20,
                                        "airline": {
                                          "id": 2,
                                          "name": "Pegasus"
                                        },
                                        "route": {
                                          "id": 5,
                                          "name": "Eskişehir Route",
                                          "airport": {
                                            "id": 4,
                                            "name": "Eskişehir Havalimanı"
                                          }
                                        }
                                      }
                                    }
                                  ]
            
                * http://localhost:8080/tickets (POST) -> Available servisinden gelen ticket'lardan birini sisteme
                    kaydetmemizi sağlar ve böylece o ticket alınmış olur. Eğer tekrar bilet almak istersek güncel
                    bilet ve fiyatlarını doğru alabilmemiz için available servisi call etmemiz gerekir.
                
                    * Request -> {
                                     "price": 10,
                                     "number": 1864,
                                     "flight": {
                                       "id": 7,
                                       "name": "Turk Hava Yolları İstanbul Uçuşu",
                                       "capacity": 10,
                                       "price": 10,
                                       "airline": {
                                         "id": 1,
                                         "name": "Turk Hava Yolları"
                                       },
                                       "route": {
                                         "id": 6,
                                         "name": "İstanbul Route",
                                         "airport": {
                                           "id": 3,
                                           "name": "İstanbul Havalimanı"
                                         }
                                       }
                                     }
                                   }
                                 
                    * Response -> {
                                    "id": 9,
                                    "price": 10,
                                    "number": 1864,
                                    "flight": {
                                      "id": 7,
                                      "name": "Turk Hava Yolları İstanbul Uçuşu",
                                      "capacity": 10,
                                      "price": 10,
                                      "airline": {
                                        "id": 1,
                                        "name": "Turk Hava Yolları"
                                      },
                                      "route": {
                                        "id": 6,
                                        "name": "İstanbul Route",
                                        "airport": {
                                          "id": 3,
                                          "name": "İstanbul Havalimanı"
                                        }
                                      }
                                    }
                                  }            
                
                * http://localhost:8080/tickets (GET) -> Sistemde var olan ticket'ları getirir.
                    
                    * Response -> [
                                    {
                                      "id": 9,
                                      "price": 10,
                                      "number": 2317,
                                      "flight": {
                                        "id": 7,
                                        "name": "Turk Hava Yolları İstanbul Uçuşu",
                                        "capacity": 10,
                                        "price": 10,
                                        "airline": {
                                          "id": 1,
                                          "name": "Turk Hava Yolları"
                                        },
                                        "route": {
                                          "id": 6,
                                          "name": "İstanbul Route",
                                          "airport": {
                                            "id": 3,
                                            "name": "İstanbul Havalimanı"
                                          }
                                        }
                                      }
                                    },
                                    {
                                      "id": 10,
                                      "price": 11,
                                      "number": 8847,
                                      "flight": {
                                        "id": 7,
                                        "name": "Turk Hava Yolları İstanbul Uçuşu",
                                        "capacity": 10,
                                        "price": 10,
                                        "airline": {
                                          "id": 1,
                                          "name": "Turk Hava Yolları"
                                        },
                                        "route": {
                                          "id": 6,
                                          "name": "İstanbul Route",
                                          "airport": {
                                            "id": 3,
                                            "name": "İstanbul Havalimanı"
                                          }
                                        }
                                      }
                                    }
                                  ]
                                  
                * http://localhost:8080/tickets/filter?flightName=turk&number=2317 (POST) -> Hem ticket number'a göre
                    hemde ticket'a ait flight ismine göre arama yapar.
                                     
                      * Response -> [
                                      {
                                        "id": 9,
                                        "price": 10,
                                        "number": 2317,
                                        "flight": {
                                          "id": 7,
                                          "name": "Turk Hava Yolları İstanbul Uçuşu",
                                          "capacity": 10,
                                          "price": 10,
                                          "airline": {
                                            "id": 1,
                                            "name": "Turk Hava Yolları"
                                          },
                                          "route": {
                                            "id": 6,
                                            "name": "İstanbul Route",
                                            "airport": {
                                              "id": 3,
                                              "name": "İstanbul Havalimanı"
                                            }
                                          }
                                        }
                                      }
                                    ]
                                    
                * http://localhost:8080/tickets/cancel/9 (DELETE) -> Id si verilen ticket'ın silinmesini sağlar.
                    Böylelikle ticket'ımız iptal olmuş olur.
                                                     
                                      * Response -> Success.                  
                                                                                                         

            
        








