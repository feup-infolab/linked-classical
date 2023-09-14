FROM php:7.4-cli

# Set working directory
WORKDIR /var/www/app/

# Copy composer.lock and composer.json
COPY  composer.json /var/www/app/
COPY package-lock.json package.json /var/www/app/


# Update packages and install Laravel dependencies
RUN apt-get update && apt-get install -y \
        g++ \
        libicu-dev \
        libpq-dev \
        libzip-dev \
        zip \
        zlib1g-dev \
        curl \
        gnupg \
    && docker-php-ext-install \
        intl \
        opcache \
        pdo \
        pdo_pgsql \
        pgsql

# Install NodeJS
RUN curl -sL https://deb.nodesource.com/setup_14.x  | bash -
RUN apt-get -y install nodejs
RUN npm install -g concurrently

# Clear cache
RUN apt-get clean && rm -rf /var/lib/apt/lists/*

# Install composer
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

# Add user for laravel application
RUN groupadd -g 1000 www
RUN useradd -u 1000 -ms /bin/bash -g www www

# Copy existing application directory contents
COPY . /var/www/app/

RUN rm -rf node_modules
RUN rm -rf vendor

# Copy existing application directory permissions
RUN chown root:root /var/www/
RUN chmod 755 /var/www/

RUN chown -R www:www /var/www/app/
RUN chmod -R 774 /var/www/app/

# Change current user to www
USER www

# Install app dependencies
RUN composer install
RUN npm install

EXPOSE 8000

ENTRYPOINT concurrently "npm run watch" "php artisan serve --host 0.0.0.0"
