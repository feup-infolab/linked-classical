const colors = require('tailwindcss/colors')

module.exports = {
  purge: [
    './resources/**/*.blade.php',
    './resources/**/*.js',
  ],
  darkMode: false, // or 'media' or 'class'
  theme: {
    screens: {
      sm: '640px',
      md: '768px',
      lg: '1024px',
      xl: '1280px',
    },
    fontSize: {
      'xs': '.75rem',
      'sm': '.875rem',
      'base': '1rem',
      'lg': '1.125rem',
      'xl': '1.25rem',
      '2xl': '1.5rem',
      '3xl': '1.875rem',
      '4xl': '2rem',
      '5xl': '3rem',
    },
    fontWeight: {
      'extra-light': 100,
      thin: 200,
      light: 300,
      normal: 400,
      medium: 500,
      semibold: 600,
      bold: 700,
      extrabold: 800,
      black: 900,
    },
    colors: {
      transparent: 'transparent',
      current: 'currentColor',
      gray: {
        dark: '#5a5a5a',
        DEFAULT: '#848484',
        light: '#ededed',
        lightest: '#f8f8f8',
      },
      white: colors.white,
      nswhite: '#fbfbfb',
      black: '#0b0b0b',
      nsblack: '#141414',
      blue: '#0000ee'
    },
    extend: {
      transitionProperty: {
        'max-h': 'max-height',
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
